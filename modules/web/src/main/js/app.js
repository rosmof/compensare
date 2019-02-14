'use strict';

import ReactDOM from 'react-dom';
import React from 'react';
import PropTypes from 'prop-types'


class ChildComponent extends React.Component {
    static propTypes = {
        name: PropTypes.string.isRequired,
    }

    static defaultProps = function () {
        console.log('child default props');
        return {}
    }

    constructor(props) {
        super(props);
        console.log('ChildComponent constructor and state');
        this.state = {name: 'Alex'};
    }

    componentWillMount() {
        console.log('ChildComponent: component will mount');
    }

    componentDidMount() {
        console.log('ChildComponent: component did mount');
    }

    render() {
        if (this.state.oops) {
            throw new Error('Something went wrong');
        }
        console.log('ChildComponent: render');
        return (
            <div key="childComponent">Name {this.state.name}</div>
        )
    }
}

class ParentComponent extends React.Component {
    static defaultProps = () => {
        console.log('ParentComponent: defaultProps');
        return {
            true: false
        }
    };

    constructor(props) {
        super(props);
        console.log('ParentComponent: constructor and state');
        this.state = {text: ''};
        this.onInputChange = this.onInputChange.bind(this);
    }

    componentWillMount() {
        console.log('ParentComponent: component will mount');
    }

    componentDidMount() {
        console.log('ParentComponent: component did mount');
    }


    onInputChange(e) {
        const text = e.target.value;
        this.setState(() => ({text: text}));
    }

    render() {
        return [
            <h2 key="h2">Learn about rendering and lifecycle methods!</h2>,
            <input key="input" value={this.state.text}
                   onChange={this.onInputChange}/>,
            <ChildComponent key="ChildComponent" name={this.state.text}/>
        ]

    }
}

ReactDOM.render(<ParentComponent/>, document.getElementById('root'));