const InvestmentRepositoryContract = require("./InvestmentRepositoryContract");
const db = require("./database");
const { v4: uuidv4 } = require('uuid');

class InvestmentRepository extends InvestmentRepositoryContract {
    constructor(dbConnection) {
        super();
        this.dbConnection = dbConnection;
    }

    getId() {
        return uuidv4();
    }


    save(investment) {
        const sql = "INSERT INTO investment (id,name,description,interest,starting_amount,final_amount,start_date,end_date) VALUES (?,?,?,?,?,?,?,?)";
        const params = [investment.id, investment.name, investment.description, investment.interest, investment.startingAmount, investment.finalAmount, investment.startDate]
        db.run(sql, params, (err, result) => {
            if (err) {
                res.status(400).json({ "error": err.message })
                return;
            }
            /*result.json({
                "message": "success",
            })*/
            console.log(err);
        })
    }

    getInvestmentFromDB(id) {
        const sql = `SELECT * FROM investment where id = '${id}'`;
        const params = []
        console.log(sql)
        return new Promise((resolve, reject) => {
            db.get(sql, params, (err, result) => {
                if (err) {
                    reject(err)
                    console.log(err)
                    return;
                }
                console.log(result)
                resolve(result);
            })
        })
    }

    ejercicioSesion07(name) {
        const sql = `SELECT * FROM investment where name = '${name}'`;
        const params = []
        console.log(sql)
        return new Promise((resolve, reject) => {
            db.get(sql, params, (err, result) => {
                if (err) {
                    reject(err)
                    console.log(err)
                    return;
                }
                console.log(result)
                resolve(result);
            })
        })
    }


}

module.exports = InvestmentRepository