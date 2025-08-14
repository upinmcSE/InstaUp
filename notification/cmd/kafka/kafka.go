package kafka

import (
	"context"
	"encoding/json"
	"fmt"
	"strings"
	"time"

	"github.com/gin-gonic/gin"
	kafka "github.com/segmentio/kafka-go"
)

var (
	KafkaProducer *kafka.Writer
)

const(
	KafkaURL = "localhost:19092"
	KafkaTopic = "user_topic_vip"
)

// for Producer
func GetKafkaWriter(kafkaURL, topic string) *kafka.Writer {
	return &kafka.Writer{
		Addr: kafka.TCP(kafkaURL),
		Topic: topic,
		Balancer: &kafka.LeastBytes{}, // cân bằng tải
	}
}


// for Consumer
func GetKafkaReader(kafkaURL, topic, groupID string) *kafka.Reader {
	brokers := strings.Split(kafkaURL, ",")
	return kafka.NewReader(kafka.ReaderConfig{
		Brokers: brokers, // []string{"localhost1", "localhost2"}
		GroupID: groupID,
		Topic: topic,
		MinBytes: 10e3, // 10KB
		MaxBytes: 10e6, // 10MB
		CommitInterval: time.Second,
		// StartOffset: kafka.FirstOffset,
		StartOffset: kafka.LastOffset,
	})
}


type StockInfo struct{
	Message string `json:"message"`
	Type string `json:"type"`
}

func newStock(msg, typeMsg string) *StockInfo{
	s := StockInfo{}
	s.Message = msg
	s.Type = typeMsg

	return &s
}

func ActionStock(c *gin.Context){
	s := newStock(c.Query("msg"), c.Query("type"))
	body := make(map[string]interface{})
	body["action"] = "action"
	body["info"] = s

	jsonBody, _ := json.Marshal(body)

	// create message
	msg := kafka.Message{
		Key: []byte("action"),
		Value: []byte(jsonBody),
	}

	// write message by producer
	err := KafkaProducer.WriteMessages(context.Background(), msg)
	if err != nil {
		c.JSON(200, gin.H{
			"err": err.Error(),
		})
		return
	}

	c.JSON(200, gin.H{
		"err": "",
		"msg": "action Successfully",
	})
}

// Consumer lắng nghe để mua ATC
func RegisterConsumerATC(id int){
	// group consumer
	// kafkaGroupId := "consumer-group-"
	kafkaGroupId := fmt.Sprintf("consumer-group-%d", id)
	reader := GetKafkaReader(KafkaURL, KafkaTopic, kafkaGroupId)
	defer reader.Close()

	fmt.Printf("Consumer(%d) Hóng Phiên ATC::", id)
	for{
		m, err := reader.ReadMessage(context.Background())
		if err != nil {
			fmt.Printf("Consumer(%d) error: %v", id, err)
		}
		fmt.Printf("Consumer(%d), hóng topic:%v, partition:%v, offset:%v, time:%d %s = %s\n", id, m.Topic, m.Partition, m.Offset, m.Time.Unix(), string(m.Key), string(m.Value))

	}
}



