//
//  SourceReport.swift
//  ThirstQuencher
//
//  Created by Wiqas Nassar on 3/28/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class PurityReport: NSObject {
    private let name, location, waterCondition, virusPPM, contaminantPPM: String
    private let date: Date
    private let number: Int
    
    init(date: Date, number: Int, name: String, location: String, waterCondition: String, virusPPM: String,
         contaminantPPM: String) {
        self.date = date
        self.number = number
        self.name = name
        self.location = location
        self.waterCondition = waterCondition
        self.virusPPM = virusPPM
        self.contaminantPPM = contaminantPPM
    }
    
    func getDate() -> Date {
        return date
    }
    
    func getNum() -> Int {
        return number
    }
    
    func getName() -> String {
        return name
    }
    
    func getLocation() -> String {
        return location
    }
    
    func getWaterCondition() -> String {
        return waterCondition
    }
    
    func getVirusPPM() -> String {
        return virusPPM
    }
    
    func getContaminantPPM() -> String {
        return contaminantPPM
    }
    
}
