
const Discord = require('discord.js');
const commando = require('discord.js-commando');
const bot = new Discord.Client();    // connects to server and handles details

var fs = require('fs');
bot.on('message', (message) => {    // any time a message is sent in a channel run this piece of code

if(message.channel.id == ("CHANNELNAME")){              //CHANNEL NAME GOES HERE 
        var ourString = message.author.username;
        ourString = ourString + ',';
        ourString = ourString + message;
        fs.writeFile("guns.txt",ourString,function(err){
            if(err){
                return console.log(err);
                        }
                        console.log("File was saved") 
        });
}
});

bot.login('BOTID');   // BOTID GOES HERE in between the quotes