# realtime-asterisk-postgres
  A work in progress asterisk based call-center software
### Features:
  
  * React frontend to manage asterisk resources
  * Springboot backend API
  * Okta auth for the security of the routes
  * Postgres for the realtime asterisk config

## How to run

``` docker-compose up ```

* Add endpoints from: localhost:3000
* Login to the sip softphone using your host IP
* dial any number and enjoy!

### How to manage extensions/dialplan?

  Note: To manage dialplan you need to add one line like below in the extensions.conf

  ```
  [context_name]
  switch => Realtime/context_name@extensions
  ```
  then add extensions from the front-end with above context