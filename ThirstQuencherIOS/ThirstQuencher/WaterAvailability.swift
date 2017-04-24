//
//  WaterAvailabilityViewController.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 3/2/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit
import GoogleMaps

class WaterAvailabilityViewController: UIViewController {
    
    @IBOutlet weak var mapViewOG: GMSMapView!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        mapViewOG.camera = GMSCameraPosition.camera(withLatitude: 32.10, longitude: -83.23, zoom: 6.0)
        let marker = GMSMarker()
        marker.position = CLLocationCoordinate2D(latitude: 32.10, longitude: -83.23)
        marker.title = "Atlanta"
        marker.snippet = "GA"
        marker.map = mapViewOG
        marker.isDraggable = true
        
        let camera = GMSCameraPosition.camera(withLatitude: 32.10, longitude: -83.23, zoom: 6.0)
        let mapView = GMSMapView.map(withFrame: CGRect.zero, camera: camera)
        mapView.isMyLocationEnabled = true
        //mapViewOG = mapView
        
    

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    /*override func loadView() {
        
    }*/

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
