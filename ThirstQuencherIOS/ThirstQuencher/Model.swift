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
    private init() {}
    
    private var users = [String: User]()
    
    private var currentUser: User?
    
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
}
