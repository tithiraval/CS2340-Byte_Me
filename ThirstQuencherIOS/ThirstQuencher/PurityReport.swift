//
//  SourceReport.swift
//  ThirstQuencher
//
//  Created by Wiqas Nassar on 3/28/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class PurityReport: NSObject {
    private let name, waterCondition, virusPPM, contaminantPPM: String
    private let date: Date
    private let number: Int
    private let latitude, longitude: Double
    
    init(date: Date, number: Int, name: String, latitude: Double, longitude:Double, waterCondition: String, virusPPM: String,
         contaminantPPM: String) {
        self.date = date
        self.number = number
        self.name = name
        self.waterCondition = waterCondition
        self.virusPPM = virusPPM
        self.contaminantPPM = contaminantPPM
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
    
    func getWaterCondition() -> String {
        return waterCondition
    }
    
    func getVirusPPM() -> String {
        return virusPPM
    }
    
    func getContaminantPPM() -> String {
        return contaminantPPM
    }
    
    func toDict() -> Any {
        return [
            "condition": waterCondition,
            "interval": date.timeIntervalSince1970,
            "name": name,
            "latitude": latitude,
            "longitude": longitude,
            "reportNumber": number,
            "virusPPM": virusPPM,
            "contaminantPPM": contaminantPPM
        ]
    }
    
    override func isEqual(_ object: Any?) -> Bool {
        if let object = object as? PurityReport {
            return getNum() == object.getNum()
        } else {
            return false
        }
    }
    
}
