import axios from 'axios';
import { useOktaAuth } from '@okta/okta-react/';

export default axios.create({
    baseURL: `http://localhost:8080/api/v1/`
});
