//
//  Model.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 2/23/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import Foundation
import UIKit
import Firebase

class Model {
    static let sharedInstance = Model()
    
    private var sourceReports = [SourceReport]()
    private var purityReports = [PurityReport]()
    
    private var historicalYear = 0
    private var historicalLocation = ""
    
    private var lat = 32.10
    private var long = -83.23
    
    private var modifying = 2
    
    private var currentUser: FIRUser?
    
    private var ref: FIRDatabaseReference!
    
    
    private init() {
        ref = FIRDatabase.database().reference(withPath: "USERS")
    }
    
    func setCurrentUser() {
        currentUser = FIRAuth.auth()?.currentUser

    }
    
    func addUser(fromViewController: UIViewController, name: String, id: String, password: String, accountType: AccountType, emailAddress: String) {
        
        let newUser = User(name: name, id: id, password: password, accountType: accountType, emailAddress: emailAddress)
        
        let registerConfirmed = UIAlertController(title: "Registration confirmed!", message: "Person was registered in the system.", preferredStyle: UIAlertControllerStyle.alert)
        registerConfirmed.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
        registerConfirmed.addAction(UIAlertAction(title: "Sign In", style: UIAlertActionStyle.default, handler: { action in
            fromViewController.performSegue(withIdentifier: "showSignIn", sender: nil)
        }))
        
        FIRAuth.auth()?.createUser(withEmail: emailAddress, password: password) {(user, error) in
            if error != nil {
                var message = ""
                if let errCode = FIRAuthErrorCode(rawValue: error!._code) {
                    
                    switch errCode {
                    case .errorCodeInvalidEmail:
                        message = "The email inputted is invalid"
                    case .errorCodeEmailAlreadyInUse:
                        message = "The email inputted is already in use"
                    case .errorCodeWeakPassword:
                        message = "The password is too weak (shorter than 6 char)"
                    default:
                        print("Create User Error: \(error)")
                    }
                }
                let alert = UIAlertController(title: "Error", message: message, preferredStyle: UIAlertControllerStyle.alert)
                alert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
                fromViewController.present(alert, animated: true, completion: nil)
            } else {
                FIRAuth.auth()?.signIn(withEmail: emailAddress, password: password) {(user, error) in
                    // ...
                }
                let userID = FIRAuth.auth()?.currentUser?.uid
                let userDict = newUser.toDict()
                self.ref.child(userID!).setValue(userDict)
                fromViewController.present(registerConfirmed, animated: true, completion:nil)
            }
        }
    }
    
    func logIn(fromViewController: UIViewController, email: String, password: String) {
        FIRAuth.auth()?.signIn(withEmail: email, password: password) { (user, error) in
            if error != nil {
                var message = ""
                if let errCode = FIRAuthErrorCode(rawValue: error!._code) {
                    
                    switch errCode {
                    case .errorCodeInvalidEmail:
                        message = "The email inputted is invalid"
                    case .errorCodeUserDisabled:
                        message = "The user's account is disabled"
                    case .errorCodeWrongPassword:
                        message = "The password is invalid"
                    case .errorCodeOperationNotAllowed:
                        message = "Email and password accounts are not enabled"
                    case .errorCodeUserNotFound:
                        message = "User not found"
                    default:
                        print("Create User Error: \(error)")
                    }
                }
                let alert = UIAlertController(title: "Error", message: message, preferredStyle: UIAlertControllerStyle.alert)
                alert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
                fromViewController.present(alert, animated: true, completion: nil)
            } else {
                self.setCurrentUser()
                self.populateReports()
                fromViewController.performSegue(withIdentifier: "toSuccessfulLogin", sender: nil)
            }
        }
    }
    
    func logout() {
        try! FIRAuth.auth()!.signOut()
        setCurrentUser()
        currentUser = nil
    }
    
    func checkIfUSER(fromViewController: UIViewController) {
        ref.child(currentUser!.uid).observeSingleEvent(of: .value, with: {(snapshot) in
            let value = snapshot.value as? NSDictionary
            let accountType = value?["accountType"] as? String ?? ""
            if (accountType == "USER") {
                if (fromViewController is LoggedInViewController) {
                    (fromViewController as! LoggedInViewController).purityReportButton.isHidden = true
                } else if (fromViewController is WaterReports) {
                    (fromViewController as! WaterReports).navigationController?.setToolbarHidden(true, animated: true)
                }
            } else {
                if (fromViewController is WaterReports) {
                    (fromViewController as! WaterReports).navigationController?.setToolbarHidden(false, animated: true)
                    if (accountType != "WORKER") {
                        (fromViewController as! WaterReports).isManager = true
                        (fromViewController as! WaterReports).toggle.title = "View Purity Graph"
                    }
                }
            }
        })
    }
    
    func editUser(name: String, email: String, address: String) {
        let userID = currentUser!.uid
        ref.child(userID).observeSingleEvent(of: .value, with: {(snapshot) in
            let value = snapshot.value as? NSDictionary
            let accountType = value?["accountType"] as? String ?? ""
            let password = value?["password"] as? String ?? ""
            let title = value?["title"] as? String ?? ""
            let username = value?["username"] as? String ?? ""
            let dict = [
                "accountType": accountType,
                "emailAddress": email,
                "homeAddress": address,
                "password": password,
                "title": title,
                "username": username,
                "Name": name
            ]
            self.ref.child(userID).setValue(dict)
        })
    }
    
    func getCurrentUserName(from: UIViewController) {
        if (from is EditUserTableViewController) {
            ref.child(currentUser!.uid).observeSingleEvent(of: .value, with: {(snapshot) in
                let value = snapshot.value as? NSDictionary
                let name = value?["Name"] as? String ?? ""
                (from as! EditUserTableViewController).nameField.text = name
                (from as! EditUserTableViewController).name = name
            })
        } else if (from is PurityReportTableViewController || from is SourceReportTableViewController) {
            ref.child(currentUser!.uid).observeSingleEvent(of: .value, with: {(snapshot) in
                let value = snapshot.value as? NSDictionary
                let name = value?["Name"] as? String ?? ""
                if (from is PurityReportTableViewController) {
                    (from as! PurityReportTableViewController).numberLabel.text = "Reported by " + name
                    (from as! PurityReportTableViewController).currentUserName = name
                } else {
                    (from as! SourceReportTableViewController).numberLabel.text = "Reported by " + name
                    (from as! SourceReportTableViewController).currentUserName = name
                }
                
            })
        }
    }
    
    func getCurrentUserEmail(from: UIViewController) {
        if (from is EditUserTableViewController) {
            ref.child(currentUser!.uid).observeSingleEvent(of: .value, with: {(snapshot) in
                let value = snapshot.value as? NSDictionary
                let email = value?["emailAddress"] as? String ?? ""
                (from as! EditUserTableViewController).emailField.text = email
                (from as! EditUserTableViewController).email = email
            })
            
        }
    }
    
    func getCurrentUserAddress(from: UIViewController) {
        if (from is EditUserTableViewController) {
            ref.child(currentUser!.uid).observeSingleEvent(of: .value, with: {(snapshot) in
                let value = snapshot.value as? NSDictionary
                let address = value?["homeAddress"] as? String ?? ""
                (from as! EditUserTableViewController).homeField.text = address
                (from as! EditUserTableViewController).address = address
            })
            
        }
    }
    
    func populateReports() {
        let sourceReportRef = FIRDatabase.database().reference(withPath: "IOS_SOURCE_REPORTS")
        sourceReportRef.observe(.value, with: {snapshot in
            for item in snapshot.children {
                let sourceReport = (item as! FIRDataSnapshot).value as! [String: AnyObject]
                let condition = sourceReport["condition"] as! String
                let name = sourceReport["name"] as! String
                let latitude = sourceReport["latitude"] as! Double
                let longitude = sourceReport["longitude"] as! Double
                let reportNum = sourceReport["reportNumber"] as! Int
                let type = sourceReport["type"] as! String
                let date = Date(timeIntervalSince1970: sourceReport["interval"] as! Double)
                let newSourceReport = SourceReport(date: date, number: reportNum, name: name, latitude: latitude, longitude: longitude, waterType: type, waterCondition: condition)
                if (!self.sourceReports.contains(newSourceReport)) {
                    self.sourceReports.append(newSourceReport)
                }
                
            }
        })
        let purityReportRef = FIRDatabase.database().reference(withPath: "IOS_PURITY_REPORTS")
        purityReportRef.observe(.value, with: {snapshot in
            for item in snapshot.children {
                let purityReport = (item as! FIRDataSnapshot).value as! [String: AnyObject]
                let condition = purityReport["condition"] as! String
                let name = purityReport["name"] as! String
                let latitude = purityReport["latitude"] as! Double
                let longitude = purityReport["longitude"] as! Double
                let reportNum = purityReport["reportNumber"] as! Int
                let virusPPM = purityReport["virusPPM"] as! Int
                let contPPM = purityReport["contaminantPPM"] as! Int
                let date = Date(timeIntervalSince1970: purityReport["interval"] as! Double)
                let newPurityReport = PurityReport(date: date, number: reportNum, name: name, latitude: latitude, longitude: longitude, waterCondition: condition, virusPPM: virusPPM, contaminantPPM: contPPM)
                if (!self.purityReports.contains(newPurityReport)) {
                    self.purityReports.append(newPurityReport)
                }
                
            }
        })
    }
    
    func addNewSourceReport(date: Date, latitude: Double, longitude: Double, waterType: String, waterCondition: String, name: String) -> Bool {
        let otherref = FIRDatabase.database().reference(withPath: "IOS_SOURCE_REPORTS")
        let newSourceReport = SourceReport(date: date, number: (sourceReports.count + 1), name: name, latitude: latitude, longitude: longitude, waterType: waterType, waterCondition: waterCondition)
        let reportDict = newSourceReport.toDict()
        otherref.child(String(newSourceReport.getNum())).setValue(reportDict)
        return true
    }
    
    func addNewPurityReport(date: Date, latitude: Double, longitude: Double, waterCondition: String, virusPPM: Int, contaminantPPM: Int, name: String) -> Bool {
        let otherref = FIRDatabase.database().reference(withPath: "IOS_PURITY_REPORTS")
        let newPurityReport = PurityReport(date: date, number: (purityReports.count + 1), name: name, latitude: latitude, longitude: longitude, waterCondition: waterCondition, virusPPM: virusPPM, contaminantPPM: contaminantPPM)
        let reportDict = newPurityReport.toDict()
        otherref.child(String(newPurityReport.getNum())).setValue(reportDict)
        return true
    }
    
    func setHistoricalValues(year: Int, location: String) {
        historicalYear = year
        historicalLocation = location
    }
    
    func setLatLong(latitude: Double, longitude: Double) {
        self.lat = latitude
        self.long = longitude
    }
    
    func modifyingReport(which: Int) {
        modifying = which // 0 is source, 1 is purity
    }
    
    func getModifying() -> Int {
        return modifying
    }
    
    func getLat() -> Double {
        return lat
    }
    
    func getLong() -> Double {
        return long
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    func getAllSourceReports() -> [SourceReport] {
        return sourceReports
    }
    
    func getAllPurityReports() -> [PurityReport] {
        return purityReports
    }
    
    
    func getNewSourceReportNumber() -> Int {
        return sourceReports.count + 1
    }
    
    func getNewPurityReportNumber() -> Int {
        return purityReports.count + 1
    }

}
