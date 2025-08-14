package eureka

import (
	"fmt"
	"net/http"
)

func (c *EurekaClient) Heartbeat() error {
	url := fmt.Sprintf("%s/eureka/apps/%s/%s", c.BaseURL, c.AppName, c.InstanceID)
	req, err := http.NewRequest("PUT", url, nil)
	if err != nil {
		return err
	}

	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		return err
	}
	defer resp.Body.Close()

	if resp.StatusCode >= 300 {
		return fmt.Errorf("heartbeat failed: %s", resp.Status)
	}
	return nil
}
