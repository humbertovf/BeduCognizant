const express = require('express')
const bodyParser = require('body-parser');
const Investment = require("./Investment");
const InvestmentRepository = require("./InvestmentRepository");
const cors = require('cors');

const app = express();
app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())
app.use(bodyParser.raw())
app.use(cors())


app.post('/add-investment', (req, res) => {

    const investmentData = {
        name: req.body.name,
        description: req.body.description,
        interest: req.body.interest,
        startingAmount: req.body.startingAmount,
        startDate: new Date(req.body.startDate),
        duration: req.body.duration,
    };
    const repository = new InvestmentRepository()

    const investment = Investment.addInvestment(investmentData.name, investmentData.description, parseInt(investmentData.interest), parseInt(investmentData.startingAmount), investmentData.startDate, parseInt(investmentData.duration), repository);
    console.log(investment)
    repository.save(investment)
    res.send(investment);
})

app.get('/returnDBValues', async (req, res) => {
    const repository = new InvestmentRepository();
    const response = await repository.getInvestmentFromDB(req.query.id);
    res.send({ data: response });
})

app.get('/ejercicio', async (req, res) => {
    const repository = new InvestmentRepository();
    const response = await repository.ejercicioSesion07(req.query.name);
    res.send({ data: response });
})


module.exports = app;
