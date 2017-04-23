//
//  SourceReport.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 3/1/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class SourceReport: NSObject {
    private let name, location, waterType, waterCondition: String
    private let date: Date
    private let number: Int
    
    init(date: Date, number: Int, name: String, location: String, waterType: String, waterCondition: String) {
        self.date = date
        self.number = number
        self.name = name
        self.location = location
        self.waterType = waterType
        self.waterCondition = waterCondition
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
    
    func getWaterType() -> String {
        return waterType
    }
    
    func getWaterCondition() -> String {
        return waterCondition
    }
    
    func toDict() -> Any {
        return [
            "condition": waterCondition,
            "interval": date.timeIntervalSince1970,
            "name": name,
            "location": location,
            "reportNumber": number,
            "type": waterType,
        ]
    }
    
    override func isEqual(_ object: Any?) -> Bool {
        if let object = object as? SourceReport {
            return getNum() == object.getNum()
        } else {
            return false
        }
    }

}
