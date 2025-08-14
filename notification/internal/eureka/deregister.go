package eureka

import (
	"fmt"
	"net/http"
)

func (c *EurekaClient) Deregister() error {
	url := fmt.Sprintf("%s/eureka/apps/%s/%s", c.BaseURL, c.AppName, c.InstanceID)
	req, err := http.NewRequest("DELETE", url, nil)
	if err != nil {
		return err
	}

	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		return err
	}
	defer resp.Body.Close()

	if resp.StatusCode >= 300 {
		return fmt.Errorf("failed to deregister: %s", resp.Status)
	}
	return nil
}
