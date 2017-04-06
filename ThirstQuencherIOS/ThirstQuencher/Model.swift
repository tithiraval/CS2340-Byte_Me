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
    
    private var users = [String: User]()
    private var sourceReports = [SourceReport]()
    private var purityReports = [PurityReport]()
    
    private var currentUser: User?
    
    var ref: FIRDatabaseReference!
    
    private init() {
        addUser(name: "Test User", id: "u", password: "p", accountType: AccountType.USER)
        addUser(name: "Test Worker", id: "w", password: "p", accountType: AccountType.WORKER)
        ref = FIRDatabase.database().reference(withPath: "users")
        FIRAuth.auth()?.createUser(withEmail: "TestUser@whatever.com", password: "p") {(user, error) in
            //print(error)
        }
        /*
        FIRAuth.auth()?.signIn(withEmail: "dhurvgarg@gmail.com", password: "password") { (user, error) in
            // ...
        }
        ref = FIRDatabase.database().reference(withPath: "users")
        let user = User(name: "Test User", id: "u", password: "p", accountType: AccountType.USER)
        ref.child("Test User 1").setValue(user.toDict())
        */
    }
    
    func addUser(name: String, id: String, password: String, accountType: AccountType) -> Bool {
        
        let newUser = User(name: name, id: id, password: password, accountType: accountType)
        
        //ref.child(name).setValue(newUser.toDict())
        if (users[id] == nil) {
            users[id] = newUser
            return true
        } else {
            return false
        }
 
    }
    
    func checkUser(id: String, password: String) -> String {
        if (users[id] == nil) {
            return "Username does not exist"
        } else if (users[id]!.getPassword() != password) {
            return "Password does not exist"
        } else {
            currentUser = users[id]
            return "Login Succeeded"
        }
        
    }
    
    func editUser(name: String, email: String, address: String) {
        currentUser!.setName(name: name)
        currentUser!.setEmailAddress(email: email)
        currentUser!.setHomeAddress(address: address)
    }
    
    func addNewSourceReport(date: Date, location: String, waterType: String, waterCondition: String) -> Bool {
        let newSourceReport = SourceReport(date: date, number: (sourceReports.count + 1), name: currentUser!.getName(), location: location, waterType: waterType, waterCondition: waterCondition)
        sourceReports.append(newSourceReport)
        return true
    }
    
    func addNewPurityReport(date: Date, location: String, waterCondition: String, virusPPM: String, contaminantPPM: String) -> Bool {
        let newPurityReport = PurityReport(date: date, number: (purityReports.count + 1), name: currentUser!.getName(), location: location, waterCondition: waterCondition, virusPPM: virusPPM, contaminantPPM: contaminantPPM)
        purityReports.append(newPurityReport)
        return true
    }
    
    
    func getAllSourceReports() -> [SourceReport] {
        return sourceReports
    }
    
    func getAllPurityReports() -> [PurityReport] {
        return purityReports
    }
    
    func logout() {
        currentUser = nil
    }
    
    func getCurrentUserName() -> String {
        return currentUser!.getName()
    }
    
    func getNewSourceReportNumber() -> Int {
        return sourceReports.count + 1
    }
    
    func getNewPurityReportNumber() -> Int {
        return purityReports.count + 1
    }
    
    func getCurrentUserEmail() -> String {
        return currentUser!.getEmailAddress()
    }
    
    func getCurrentUserAddress() -> String {
        return currentUser!.getHomeAddress()
    }
    
    func getCurrentUserAccountType() -> AccountType {
        return currentUser!.getAccountType()
    }
}
