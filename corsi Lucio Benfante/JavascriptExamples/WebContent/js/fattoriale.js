let fattIterative = function(n) {
	let result = 1
	let i = 2
	while (i <= n) {
		result *= i
		i++
	}
	return result;
}

let fattIterative2 = function(n) {
	let result = 1
	let i = 1
	do {
		result *= i
		i++
	} while (i <= n)
	return result;
}

let fattRecursive = function(n) {
	if (n > 1) {
		return n * fattRecursive(n - 1)
	}
	return 1
}

let fattGenerator = function*() {
	let result = 1
	let i = 0
	do {
		let reset = yield result
		if (reset === undefined) {
			i++
			result *= i
		} else {
			result = 1
			i = 0
		}
	} while (true)
}

exports.fattIter = fattIterative
exports.fattIter2 = fattIterative2
exports.fattRec = fattRecursive
exports.fattGen = fattGenerator
