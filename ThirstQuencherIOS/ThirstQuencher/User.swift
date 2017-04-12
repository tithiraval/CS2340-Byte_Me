//
//  User.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 2/23/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class User: NSObject {
    private var name: String
    private let id, password: String
    private let accountType: AccountType
    private var emailAddress: String
    private var homeAddress: String?
    
    init(name: String, id: String, password: String, accountType: AccountType, emailAddress: String) {
        self.name = name
        self.id = id
        self.password = password
        self.accountType = accountType
        self.emailAddress = emailAddress
    }
    
    func getName() -> String {
        return name
    }
    
    func getID() -> String {
        return id
    }
    
    func getPassword() -> String {
        return password
    }
    
    func getAccountType() -> AccountType {
        return accountType
    }
    
    func getEmailAddress() -> String {
        return emailAddress
    }
    
    func getHomeAddress() -> String {
        if (homeAddress == nil) {
            return ""
        }
        return homeAddress!
    }
    
    func setEmailAddress(email: String) {
        self.emailAddress = email
    }
    
    func setHomeAddress(address: String) {
        self.homeAddress = address
    }
    
    func setName(name: String) {
        self.name = name
    }
    
    func toDict() -> Any {
        let strAccountType: String
        if (accountType == AccountType.MANAGER) {
            strAccountType = "Manager"
        } else if (accountType == AccountType.WORKER) {
            strAccountType = "Worker"
        } else if (accountType == AccountType.ADMIN) {
            strAccountType = "Admin"
        } else {
            strAccountType = "User"
        }
        return [
            "Name": name,
            "ID": id,
            "Password": password,
            "Account Type": strAccountType,
            "Email Address": emailAddress,
            "Home Address": homeAddress
        ]
    }
    
}
