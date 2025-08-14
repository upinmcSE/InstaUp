package eureka

import (
	"bytes"
	"fmt"
	"net/http"
)

func (c *EurekaClient) Register() error {
	xmlBody := fmt.Sprintf(`
		<instance>
			<instanceId>%s</instanceId>
			<hostName>%s</hostName>
			<app>%s</app>
			<ipAddr>127.0.0.1</ipAddr>
			<status>UP</status>
			<port enabled="true">%d</port>
			<dataCenterInfo class="com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo">
				<name>MyOwn</name>
			</dataCenterInfo>
		</instance>`, c.InstanceID, c.HostName, c.AppName, c.Port)

	url := fmt.Sprintf("%s/eureka/apps/%s", c.BaseURL, c.AppName)
	req, err := http.NewRequest("POST", url, bytes.NewBuffer([]byte(xmlBody)))
	if err != nil {
		return err
	}
	req.Header.Set("Content-Type", "application/xml")

	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		return err
	}
	defer resp.Body.Close()

	if resp.StatusCode >= 300 {
		return fmt.Errorf("failed to register: %s", resp.Status)
	}
	return nil
}
