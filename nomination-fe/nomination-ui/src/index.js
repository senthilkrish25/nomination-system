import {StrictMode} from 'react';
import {createRoot} from 'react-dom/client';
import {BrowserRouter} from 'react-router-dom';
import ReactDOM from 'react-dom/client';
// scroll bar
import 'simplebar/src/simplebar.css';

// third-party
import {Provider as ReduxProvider} from 'react-redux';

// apex-chart
import 'assets/third-party/apex-chart.css';

// project import
import App from './App';
import {store} from 'store';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <StrictMode>
        <ReduxProvider store={store}>
            <BrowserRouter>
                <App/>
            </BrowserRouter>
        </ReduxProvider>
    </StrictMode>
);

