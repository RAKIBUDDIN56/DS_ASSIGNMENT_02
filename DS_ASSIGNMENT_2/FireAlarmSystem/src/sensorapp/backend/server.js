const express=require('express');
const app=express();

const cors=require('cors');
const mongoose=require('mongoose');


require('dotenv').config();
const port=process.env.PORT || 5000;
//course middleware
app.use(cors());
app.use(express.json());

const uri=process.env.ATLAS_URL;
mongoose.connect(uri,{useNewUrlParser:true,useCreateIndex:true,useUnifiedTopology:true});
const connection=mongoose.connection;
connection.once('open',() =>{
    console.log("Mongo db database connection established successfully");
});

const sensorRouter=require('./routes/sensor');
const userRouter=require('./routes/users');
//someone enter exercise , it will load exerciseRoute
app.use('/sensors',sensorRouter);
app.use('/users',userRouter);


//start server
app.listen(port,() =>{
    console.log('Server os running on port '+port)
});
