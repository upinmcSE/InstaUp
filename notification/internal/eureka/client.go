package eureka

import (
	"fmt"
	"log"
	"os"
	"os/signal"
	"syscall"
	"time"
)

type EurekaClient struct {
	BaseURL     string
	AppName     string
	InstanceID  string
	HostName    string
	Port        int
	IntervalSec int
	stopChan    chan struct{}
}

func NewClient(baseURL, appName, hostName string, port, intervalSec int) *EurekaClient {
	host, _ := os.Hostname()
	// instanceID := fmt.Sprintf("%s:%d", appName, port)
	instanceID := fmt.Sprintf("%s:%s:%d", host, appName, port)
	return &EurekaClient{
		BaseURL:     baseURL,
		AppName:     appName,
		InstanceID:  instanceID,
		HostName:    hostName,
		Port:        port,
		IntervalSec: intervalSec,
		stopChan:    make(chan struct{}),
	}
}

func (c *EurekaClient) Run() {
	// 1 dăng ký
	if err := c.Register(); err != nil {
		log.Fatal("Eureka register failed:", err)
	}
	fmt.Println("Registered with Eureka")

	// 2 gửi heartbeat định kỳ
	ticker := time.NewTicker(time.Duration(c.IntervalSec) * time.Second)
	go func() {
		for {
			select {
			case <-ticker.C:
				if err := c.Heartbeat(); err != nil {
					fmt.Println("Heartbeat error:", err)
				} else {
					fmt.Println("Heartbeat sent")
				}
			case <-c.stopChan:
				ticker.Stop()
				return
			}
		}
	}()

	// 3 bắt tín hiệu shutdown
	sig := make(chan os.Signal, 1)
	signal.Notify(sig, os.Interrupt, syscall.SIGTERM)
	<-sig

	// 4 dừng heartbeat & deregister
	close(c.stopChan)
	if err := c.Deregister(); err != nil {
		fmt.Println("Failed to deregister:", err)
	} else {
		fmt.Println("Deregistered from Eureka")
	}
}
