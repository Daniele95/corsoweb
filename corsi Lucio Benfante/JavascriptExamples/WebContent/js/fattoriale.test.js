const {fattIter: fattoriale, fattIter2, fattRec, fattGen} = require('./fattoriale')

const fattIter = require('./fattoriale').fattIter;

test('Iterative fact of 4 equals 24', () => {
	  expect(fattoriale(4)).toBe(24)
	})

test('Iterative fact of 4 equals 24', () => {
	  expect(fattIter(4)).toBe(24)
	})

test('Iterative fact of 0 equals 1', () => {
	  expect(fattIter(0)).toBe(1)
	})

test('Iterative2 fact of 4 equals 24', () => {
	  expect(fattIter2(4)).toBe(24)
	})

//test('Iterative2 fact of 0 equals 1', () => {
//	  expect(fattIter2(0)).toBe(1)
//	})
//
//test('Iterative2 fact of 1 equals 1', () => {
//	  expect(fattIter2(1)).toBe(1)
//	})
	
test('Recursive fact of 4 equals 24', () => {
	  expect(fattRec(4)).toBe(24)
	})
	
test('Recursive fact of 0 equals 1', () => {
	  expect(fattRec(0)).toBe(1)
	})

test('Generator sequence 1 1 2 6 24', () => {
	let sequence = fattGen()
	expect(sequence.next().value).toBe(1)
	expect(sequence.next().value).toBe(1)
	expect(sequence.next().value).toBe(2)
	expect(sequence.next().value).toBe(6)
	expect(sequence.next().value).toBe(24)
	expect(sequence.next(true).value).toBe(1)
	expect(sequence.next().value).toBe(1)
	expect(sequence.next().value).toBe(2)	
	expect(sequence.return(-1).value).toBe(-1)
	expect(sequence.next().value).toBe(undefined)
	expect(sequence.next().done).toBe(true)
})
