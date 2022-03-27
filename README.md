# log4lol
This is a simple HTTP server that logs whatever is POSTed to it using version 2.14.1 of log4j. There is also a TCP server written in Golang, meant to serve JNDI requests.

## Why?
Version 2.14.1 is the last version that is vulnerable to the log4shell exploit. Use this dummy server to play around with the vulnerability.

Have fun with this, instead of someone else's unpatched boxes plz.

## How to use
Clone repo, build using Gradle, run `LolServer`. Default port is 8000. Open [http://localhost:8000/](http://localhost:8000), fill in what you want to send, click Send.

### JNDI server
The Go program responds to *any* request with the contents of the file in the same folder with the name `payload`.

To test this, send `${jndi:ldap://127.0.0.1:8001/}` to the web server to send a request to the JNDI server. The default payload is meant to do nothing but give a valid response.

## Thanks to
* [This blog post](https://nakedsecurity.sophos.com/2021/12/13/log4shell-explained-how-it-works-why-you-need-to-know-and-how-to-fix-it/) from Naked Security 
  * for steps for setting up a vulnerable server
  * for explaining the vulnerability

## // TODOs
* Better error handling in `LolHttpHandler.handlePostRequest()`
* ...
* Profit
