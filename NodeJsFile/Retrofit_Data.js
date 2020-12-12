var express = require('express');
const app = express();
var mysql = require('mysql');

var bodyparser = require('body-parser');

var connection = mysql.createConnection({

    host:"localhost",
    user:"root",
    password:"",
    database:'retrofit'
});

app.use(bodyparser.json());
app.use(bodyparser.urlencoded({extended:true}));

app.post('/dataadded/',(req,res,next)=>{

    var data = req.body;
    var Id = data.Id;
    var ProductName = data.ProductName;
    var Category = data.Category;
    var SubCategory = data.SubCategory;
    var Price = data.Price;
            var insert_cmd = "INSERT INTO retrofit_product_details(Id,ProductName,Category,SubCategory,Price) values (?,?,?,?,?)";
            values =[Id,ProductName,Category,SubCategory,Price];

            console.log("executing: "+insert_cmd +values);
            connection.query(insert_cmd,values,(err,results,feilds)=>{
                connection.on('error',(err)=>{
                    console.log("[MySQL ERROR]",err);
                });

                res.json(results);
                console.log("Product Added Successfully.");
            });

});


app.get('/select/',(req,res)=>{

    connection.query("SELECT * FROM retrofit_product_details ",(err,result)=>{

        connection.on('error',(err)=>{
            console.log("[MySQL ERROR]",err);
        });

        res.send(JSON.stringify(result));
    });
});

var server = app.listen(3000,()=>{
    console.log("Server running at http://localhost:3000");
});