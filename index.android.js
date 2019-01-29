import { 
	DeviceEventEmitter
	// NativeModules
} from 'react-native';
// const { HeadphoneModule } = NativeModules

const required = param => {
	throw new Error("Missing Parameter: " + param)
}

function HeadphoneEvent () {
	listeners = []
	
	const addEventListener = (listener, cb) => {
		listeners.push(DeviceEventEmitter.addListener(listener, cb))
		// HeadphoneModule.addEventListener()
		return listeners.length - 1
	}

	const removeEventListener = (pos = required('pos')) => {
		listeners[pos].remove()
		listeners.splice(pos, 1)
	}

	const removeAllListeners = () => {
		listeners.forEach( i=> i.remove())
		listeners = []
	}

	return {
		addEventListener,
		removeEventListener,
		removeAllListeners
	}
}

export default HeadphoneEvent()
