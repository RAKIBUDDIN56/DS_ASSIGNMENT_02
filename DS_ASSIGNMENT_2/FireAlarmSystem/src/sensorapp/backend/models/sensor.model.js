const mongoose=require('mongoose');

const Schema=mongoose.Schema;

const sensorSchema = new Schema({
    sensorname: { type: String, required: true},
    location: {type: String, required: true},
    smokelevel: {type: Number, required: true},
    co2level: {type: Number, required:true},
    activestatus: {type: Boolean,required:true}


},{
    timestamp: false,
});

const Sensor=mongoose.model('Sensor',sensorSchema);

module.exports=Sensor;