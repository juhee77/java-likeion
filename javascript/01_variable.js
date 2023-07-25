// let
// 데이터의 형식을 따지지 않는 변수
let foo = 'a let variable'
console.log(foo)
// let 으로 선언한 변수는 재할당이 가능하다.
foo = 'let variables can be reallocated'
console.log(foo)
// 다른 타입으로 재할당도 되긴 하나 권장되지 않음
foo = 10
console.log(foo)

// const
// 불변하는 변수
const bar = 'a const variable'
console.log(bar)
// const 로 선언한 변수는 재할당이 불가하다.
// java 의 final 과 비슷한 역할
// bar = 'const variable cannot be reallocated'  // error
// 상수 선언시도 많이 활용
const PI = 3.14


// var
// let과 유사하게 동작
var baz = 'a var variable'
console.log(baz)
baz = 'var can be assigned again'

// let
// 데이터의 형식을 따지지 않는 변수
let foo = 'a let variable'
console.log(foo)
// let 으로 선언한 변수는 재할당이 가능하다.
foo = 'let variables can be reallocated'
console.log(foo)
// 다른 타입으로 재할당도 되긴 하나 권장되지 않음
foo = 10
console.log(foo)

// const
// 불변하는 변수
const bar = 'a const variable'
console.log(bar)
// const 로 선언한 변수는 재할당이 불가하다.
// java 의 final 과 비슷한 역할
// bar = 'const variable cannot be reallocated'  // error
// 상수 선언시도 많이 활용
const PI = 3.14


// var
// let과 유사하게 동작
var baz = 'a var variable'
console.log(baz)
baz = 'var can be assigned again'


// 호이스팅(hoisting)
console.log(notHoisted)  // 오류
let notHoisted = 'let variable is not hoisted'

// 아래쪽에 선언한 변수들을 코드 최상단에서 우선 선언하도록
// 코드가 변경되어, 실제 값의 할당 이전에 참조가 가능해지는 현상
console.log(hoisted)
var hoisted = 'var variable is hoisted'

// 호이스팅은 논리적 흐름을 깨뜨리기 때문에 더이상 권장되지 않는다.