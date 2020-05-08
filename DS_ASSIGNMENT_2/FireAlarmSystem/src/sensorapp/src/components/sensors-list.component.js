import React, { Component } from 'react';
import { Badge} from 'reactstrap';

import axios from 'axios';

const Sensor = props => (
    <tr>
        <td>{props.sensor.sensorname}</td>
        <td>{props.sensor.location}</td>
        <td>{props.sensor.smokelevel}</td>
        <td>{props.sensor.smokelevel}</td>
        <td>Active{props.sensor.activestatus}</td>



    </tr>
);


export default class SensorList extends Component {
    constructor(props) {
        super(props);


        this.state = {sensors: []};
    }

    componentDidMount() {

        setInterval( () =>{
        axios.get('http://localhost:5000/sensors/')
            .then(response => {
                this.setState({ sensors: response.data })
            })
            .catch((error) => {
                console.log(error);
            })
        },4000)
    }



    sensorList() {
        return this.state.sensors.map(currentsensor => {
            return <Sensor sensor={currentsensor} key={currentsensor._id}/>;
        })
    }

    render() {
        return (
            <div>
                <h3>Logged sensors</h3>
                <table className="table">
                    <thead className="thead-light">
                    <tr>
                        <th>Sensorname</th>
                        <th>Location</th>
                        <th>SmokeLevel</th>
                        <th>Co2level</th>
                        <th>Active Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    { this.sensorList() }
                    </tbody>
                </table>
            </div>
        )
    }
}