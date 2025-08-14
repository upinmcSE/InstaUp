package main

import (
	"github.com/upin/send_email/internal/eureka"
)


func main() {
	client := eureka.NewClient(
		"http://localhost:8761",
		"notification-service",
		"localhost",
		8080,
		30,
	)
	client.Run()
}

// func main() {
// 	r := gin.Default()

// 	kafka.KafkaProducer = kafka.GetKafkaWriter(kafka.KafkaURL, kafka.KafkaTopic)
// 	defer kafka.KafkaProducer.Close()

// 	r.POST("action/stock", kafka.ActionStock)

// 	// register 2 user để mua Stock trong ATC 
// 	go kafka.RegisterConsumerATC(1)
// 	go kafka.RegisterConsumerATC(2)

// 	r.Run(":8999")

// }
