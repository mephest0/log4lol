package main

import (
	"fmt"
	"io"
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

	return contents
}

func handleRequest(conn net.Conn) {
	fmt.Println("Got request, writing response")

	if len(readRequest(conn)) > 15 {
		// Payload is most likely HTTP
	} else {
		// Payload is most likely JNDI
	}

	conn.Write([]byte(getPayload()))
	conn.Close()

	fmt.Println("Connection closed")
}

func readRequest(conn net.Conn) string {
	tmp := make([]byte, 512)

	_, err := conn.Read(tmp)
	if err != nil && err != io.EOF {
		fmt.Println("!! Read error:", err)
	}
	asText := string(tmp)

	fmt.Println("Total size:", len(tmp))
	fmt.Println("Buffer", tmp)
	fmt.Println("Request", asText)

	return asText
}
