finagle-debug
=============

use `curl -v  http://localhost:8080` to test the service

Issue: **There's a IndividualRequestTimeoutException once every 8 requests**

```
SEVERE: A server service proxy-client threw an exception
com.twitter.finagle.IndividualRequestTimeoutException: exceeded 1.minutes to proxy-client while waiting for a response for an individual request, excluding retries
	at com.twitter.finagle.NoStacktrace(Unknown Source)
```
