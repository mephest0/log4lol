package main

import (
	"fmt"
	"net"
	"os"
)

const (
	FileName = "payload"
)

func main() {
	l, err := net.Listen("tcp", "localhost:8001")
	i := 0

	check(err)

	defer l.Close()

	for {
		fmt.Println("Listening...", i)
		i++

		conn, err := l.Accept()

		check(err)

		handleRequest(conn)
	}

}

func check(e error) {
	if e != nil {
		fmt.Println("Panic")
		panic(e)
	}
}

func getPayload() string {
	dat, err := os.ReadFile(FileName)
	check(err)

	var contents = string(dat)
	fmt.Println(contents)

	return contents
}

func handleRequest(conn net.Conn) {
	fmt.Println("Got request, writing response")

	conn.Write([]byte(getPayload()))
	conn.Close()
}
