import React from "react";
import {Provider} from 'react-redux';
import {store} from "./store";
import AppRoutes from "./AppRoutes";
import {
    QueryClient,
    QueryClientProvider
} from "@tanstack/react-query"

function App() {
    const queryClient = new QueryClient()

    return (
        <Provider store={store}>
            <QueryClientProvider client={queryClient}>
                <AppRoutes/>
            </QueryClientProvider>
        </Provider>
    )
}

export default App;
