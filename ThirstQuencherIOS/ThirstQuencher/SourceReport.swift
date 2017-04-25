//
//  SourceReport.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 3/1/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class SourceReport: NSObject {
    private let name, waterType, waterCondition: String
    private let date: Date
    private let number: Int
    private let latitude, longitude: Double
    
    init(date: Date, number: Int, name: String, latitude: Double, longitude:Double, waterType: String, waterCondition: String) {
        self.date = date
        self.number = number
        self.name = name
        self.waterType = waterType
        self.waterCondition = waterCondition
        self.latitude = latitude
        self.longitude = longitude
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
    
    func getLatitude() -> Double {
        return latitude
    }
    
    func getLongitude() -> Double {
        return longitude
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
            "latitude": latitude,
            "longitude": longitude,
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
