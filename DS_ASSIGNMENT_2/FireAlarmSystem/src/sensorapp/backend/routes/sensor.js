
const router=require('express').Router();
let Sensor=require('../models/sensor.model');

router.route('/').get((req,res) =>{
    Sensor.find()
        .then(sensors =>res.json(sensors))
        .catch(err =>res.status(400).json('Error'+err))
});

router.route('/add').post((req,res) =>{
    const sensorname=req.body.sensorname;
    const location=req.body.location;
    const smokelevel=req.body.smokelevel*10;
    const co2level=req.body.co2level;
    const activestatus=req.body.activestatus;


    const newSensor=new  Sensor({
        sensorname,
        location,
        smokelevel,
        co2level,
        activestatus

    });
    newSensor.save()
        .then(()=> res.json('Sensor added'))
        .catch(err => res.status(400).json('Error'+err))
});

router.route('/:id').get((req,res) =>{
    Sensor.findById(req.params.id)
        .then(sensor => res.json(sensor))
        .catch(err => res.json('Error' + err))
});

router.route('/:id').delete((req,res) =>{
    Sensor.findByIdAndDelete(req.params.id)
        .then(()=>res.json('Sensor deleted'))
        .catch(err => res.status(400).json('Error'+err));
});

router.route('/update/:id').post((req,res) =>{
    Sensor.findById(req.params.id)
        .then(sensor =>{
            sensor.sensorname=req.body.sensorname;
            sensor.location=req.body.location;
            sensor.smokelevel=req.body.smokelevel;
            sensor.co2level=req.body.co2level;
            sensor.activestatus=req.body.activestatus


            sensor.save()
                .then(() => res.json('Sensor updated'))
                .catch(err => res.status(400).json('Error'+err));
        })
        .catch((err =>res.status(400).json('Error'+err)));
});

module.exports=router;


