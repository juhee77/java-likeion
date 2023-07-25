// null
// 어떤 데이터가 의도적으로 비어졌을 경우 사용하는 데이터
let answer = null;
console.log(answer)

// undefined
// 값이 정의되지 않을때, 암시적으로 데이터가 비어 버릴때,
let answerNotDefined
console.log(answerNotDefined)
// 둘이 공존하는건 설계 오류

console.log(typeof null)
console.log(typeof undefined)


// Object
// JSON 의 Object 를 부르는 말과 동일하다.
const person = {
    firstName: 'Jeeho',
    lastName: 'Park',
    'phone number': '010-1234-5678',
}
// 객체의 요소에 접근할때는 `.` 또는 `[]` 를 사용한다.
console.log(person.firstName)

console.log(person.lastName)
console.log(person['lastName'])
console.log(person['phone number'])