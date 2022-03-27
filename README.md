# log4lol
This is a simple HTTP server that logs whatever is POSTed to it using version 2.14.1 of log4j.

## Why?
Version 2.14.1 is the last version that is vulnerable to the log4shell exploit. Use this dummy server to play around with the vulnerability.

Have fun with this, instead of someone else's unpatched boxes plz.

## How to use
Clone repo, build using Gradle, run `LolServer`. Default port is 8000. Open [http://localhost:8000/](http://localhost:8000), fill in what you want to send, click Send.

## Thanks to
* [This blog post](https://nakedsecurity.sophos.com/2021/12/13/log4shell-explained-how-it-works-why-you-need-to-know-and-how-to-fix-it/) from Naked Security 
  * for steps for setting up a vulnerable server
  * for explaining the vulnerability

## // TODO all points in this list
* Make the rest of the toolchain needed for 100% pwnage
* Better error handling in `LolHttpHandler.handlePostRequest()`
* ...
* Profit
