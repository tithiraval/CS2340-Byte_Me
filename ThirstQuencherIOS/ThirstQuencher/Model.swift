//
//  Model.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 2/23/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import Foundation
import UIKit

class Model {
    static let sharedInstance = Model()
    
    private var users = [String: User]()
    private var sourceReports = [SourceReport]()
    
    private var currentUser: User?
    
    private init() {
        addUser(name: "Test User", id: "u", password: "p", accountType: AccountType.USER)
        addUser(name: "Test Worker", id: "w", password: "p", accountType: AccountType.WORKER)
    }
    
    func addUser(name: String, id: String, password: String, accountType: AccountType) -> Bool {
        
        let newUser = User(name: name, id: id, password: password, accountType: accountType)
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
    
    func addNewReport(date: Date, location: String, waterType: String, waterCondition: String) -> Bool {
        let newReport = SourceReport(date: date, number: (sourceReports.count + 1), name: currentUser!.getName(), location: location, waterType: waterType, waterCondition: waterCondition)
        sourceReports.append(newReport)
        return true
    }
    
    
    func getAllReports() -> [SourceReport] {
        return sourceReports
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
