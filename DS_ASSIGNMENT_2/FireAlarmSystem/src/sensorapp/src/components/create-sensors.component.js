import React,{Component} from "react";
import axios from 'axios';

export default class  CreateSensor extends Component{
    constructor(props) {
        super(props);

        this.onChangeSensorName=this.onChangeSensorName.bind(this);
        this.onChangeLocation=this.onChangeLocation.bind(this);
        this.onChangeSmokelevel=this.onChangeSmokelevel.bind(this);
        this.onChangeCo2level=this.onChangeCo2level.bind(this);
        this.onChangeActivestatus=this.onChangeActivestatus.bind(this);
        this.onSubmit =this.onSubmit.bind(this);

        this.state={
            sensorname: '',
            location:'',
            smokelevel: 0,
            co2level: 0,
            activestatus: true,
            users:[]
        }
    }
    componentDidMount() {
        axios.get('http://localhost:5000/users/')
            .then(response =>{
                if ( response.data.length > 0){
                    this.setState({
                        users:response.data.map(user =>user.username),
                        sensorname: response.data[0].username
                    })
                }
            })
    }

    onChangeSensorName(e){
        this.setState({
            sensorname: e.target.value
        });
    }
    onChangeLocation(e){
        this.setState({
            location: e.target.value
        });
    }
    onChangeSmokelevel(e){
        this.setState({
            smokelevel: e.target.value
        });
    }
    onChangeCo2level(e){
        this.setState({
            co2level: e.target.value
        });
    }
    onChangeActivestatus(e){
        this.setState({
            activestatus: e.target.value
        });
    }

    onSubmit(e){
        e.preventDefault();

        const sensor= {
            sensorname: this.state.sensorname,
            location:  this.state.location,
            smokelevel: this.state.smokelevel,
            co2level: this.state.co2level,
            activestatus: this.state.activestatus
        }
        console.log(sensor);

        axios.post('http://localhost:5000/sensors/add',sensor)
            .then(res => console.log(res.data))

        window.location= '/';
    }

    render() {
        return (
            <div>
                <h3>Create new Sensor Log</h3>
                <form onSubmit={this.onSubmit}>
                    <div className='form-group'>
                        <label >Sensorname:</label>
                        <select ref='userInput' required className='form-control' value={this.state.sensorname}
                                onChange={this.onChangeSensorName}>
                            {this.state.users.map(function (user) {
                                return <option
                                    key={user} value={user}>{user}
                                </option>
                            })
                            }
                        </select>
                        <div className='form-group'>
                            <label>Location:</label>
                            <input type={'text'} required className='form-control'
                                   value={this.state.location} onChange={this.onChangeLocation}/>
                        </div>
                        <div className='form-group'>
                            <label>Smoke Level</label>
                            <input type='text' className='form-control'
                                   value={this.state.smokelevel} onChange={this.onChangeSmokelevel}/>
                        </div>

                        <div className='form-group'>
                            <label>Co2 Level</label>
                            <input type='text' className='form-control'
                                   value={this.state.co2level} onChange={this.onChangeCo2level}/>
                        </div>

                        <div className='form-group'>
                            <label>Active status</label>
                            <input type='text' className='form-control'
                                   value={this.state.activestatus} onChange={this.onChangeActivestatus}/>
                        </div>
                        <div className='form-group'>
                            <input type='submit' value='Create Sensor log' className='btn btn-primary'/>
                        </div>
                    </div>
                </form>
            </div>

        );
    }
}