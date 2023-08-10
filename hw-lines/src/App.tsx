/*
 * Copyright (C) 2023 Soham Pardeshi.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Autumn Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

import React, { Component } from "react";
import EdgeList from "./EdgeList";
import Map from "./Map";
import Edge from "./Edge";

// Allows us to write CSS styles inside App.css, any styles will apply to all components inside <App />
import "./App.css";

interface AppState {
    edges: Edge[]
}

class App extends Component<{}, AppState> { // <- {} means no props.
    constructor(props: any) {
        super(props);
        this.state = {
            edges: []
        };
    }

    render() {
        return (
            <div>
                <h1 id="app-title">Line Mapper!</h1>
                <div>
                    {/* DONE: define props in the Map component and pass them in here */}
                    <Map edges={this.state.edges}/>
                </div>
                <EdgeList
                    onChange={(value) => {
                        // DONE: Modify this onChange callback to store the edges in the state
                        this.setState({edges: value})
                    }}
                />
            </div>
        );
    }
}

export default App;