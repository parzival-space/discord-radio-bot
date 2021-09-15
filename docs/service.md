<h1 id="service">Install as a service (Linux only)</h1>

Please make sure you followed the <a href="requirements.md">requirements</a>.

Using this method, will install the bot as a service.
I still recomend using docker, but this method is here if you want to.

To install this app as a service, just run:

```bash
# this will install the packages forever and forever-service
# sudo is required to access the systemctl command
sudo yarn run install-service
```

And to remove the service:

```bash
# this will remove the service
sudo yarn run remove-service

# this will not remove forever and forever-service, you need to do that manually using
sudo yarn remove -g forever forever-service
```
