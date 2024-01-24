const mongoose = require('mongoose')

const userSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true
    },
    password: {
        type: String,
        required: true
    },
    players:{
        type: String,
        required: false,
        default: "none"
    },
    teams:{
        type: String,
        required: false,
        default: "none"
    }
})

module.exports = mongoose.model('user', userSchema)