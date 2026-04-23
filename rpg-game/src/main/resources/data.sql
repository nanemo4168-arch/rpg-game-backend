-- 보스 데이터
INSERT INTO bosses (stage, name, max_hp, current_hp, attack_power, emoji, description) VALUES
(1, '변수 골렘', 300, 300, 20, '🗿', '자바의 기초 변수와 타입을 수호하는 골렘!'),
(2, '객체 드래곤', 400, 400, 25, '🐉', '클래스와 객체지향을 왜곡하는 드래곤!'),
(3, '컬렉션 마왕', 500, 500, 30, '👹', '자바의 고급 기능을 지배하는 최종 보스!');

-- =============================================
-- STAGE 1: 변수, 타입, 조건문, 반복문 기초
-- =============================================

-- [객관식]
INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('변수와 타입', 1, 1,
'Java의 기본 정수 타입 4가지를 크기 순서대로 나열한 것은?',
'MULTIPLE_CHOICE',
'byte < short < int < long',
'short < byte < int < long',
'byte < int < short < long',
'int < short < byte < long',
'A',
'byte(1byte) < short(2byte) < int(4byte) < long(8byte) 순서입니다.',
NULL, false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('변수와 타입', 1, 1,
'다음 중 변수명으로 사용할 수 없는 것은?',
'MULTIPLE_CHOICE',
'myName', '_count', '2ndValue', 'totalSum',
'C',
'변수명은 숫자로 시작할 수 없습니다. 문자, _, $로만 시작할 수 있습니다.',
NULL, false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('연산자', 2, 1,
'다음 코드의 출력 결과는?

int x = 10;
int y = 3;
System.out.println(x % y);',
'MULTIPLE_CHOICE',
'3', '1', '0', '3.33',
'B',
'% 연산자는 나머지를 구합니다. 10 ÷ 3 = 3 나머지 1이므로 결과는 1입니다.',
NULL, true);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('조건문', 1, 1,
'if-else if-else 구문에서 else 블록이 실행되는 조건은?',
'MULTIPLE_CHOICE',
'첫 번째 if 조건이 true일 때',
'모든 if/else if 조건이 false일 때',
'else if 조건이 true일 때',
'항상 실행된다',
'B',
'else 블록은 위의 모든 if, else if 조건이 false일 때 실행됩니다.',
NULL, false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('반복문', 2, 1,
'다음 for문이 출력하는 마지막 값은?

for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}',
'MULTIPLE_CHOICE',
'4', '5', '6', '0',
'B',
'i <= 5 조건이므로 i가 5일 때까지 실행됩니다. 마지막 출력값은 5입니다.',
NULL, true);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('배열', 2, 1,
'int[] arr = new int[5]; 로 선언했을 때
arr의 마지막 인덱스는?',
'MULTIPLE_CHOICE',
'5', '4', '3', '0',
'B',
'배열 크기가 5이면 인덱스는 0~4입니다. 마지막 인덱스는 4입니다.',
NULL, false);

-- [빈칸 채우기]
INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('변수와 타입', 1, 1,
'정수형 변수를 선언하는 빈칸을 채우세요.

___ age = 25;',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'int',
'Java에서 기본 정수형 타입은 int입니다.',
'4바이트 크기의 기본 정수 타입입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('조건문', 1, 1,
'조건이 false일 때 실행되는 블록의 키워드를 채우세요.

if (score >= 90) {
    System.out.println("A");
} ___ {
    System.out.println("B");
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'else',
'조건이 거짓일 때 실행되는 블록은 else입니다.',
'if의 반대 상황을 처리하는 키워드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('반복문', 2, 1,
'while문의 반복을 즉시 종료하는 키워드를 채우세요.

while (true) {
    if (count == 10) ___; 
    count++;
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'break',
'break는 반복문을 즉시 빠져나갑니다.',
'반복문을 강제로 탈출하는 키워드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('반복문', 2, 1,
'현재 반복을 건너뛰고 다음 반복으로 가는 키워드를 채우세요.

for (int i = 0; i < 10; i++) {
    if (i % 2 == 0) ___;
    System.out.println(i);
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'continue',
'continue는 현재 반복을 건너뛰고 다음 반복으로 이동합니다.',
'현재 루프 실행을 중단하고 다음 반복을 시작하는 키워드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('변수와 타입', 2, 1,
'실수형(소수점) 변수의 타입 키워드를 채우세요.

___ pi = 3.14;',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'double',
'Java에서 기본 실수 타입은 double(8바이트)입니다. float는 f 접미사가 필요합니다.',
'소수점을 저장하는 8바이트 기본 실수 타입입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('참조 타입', 2, 1,
'배열의 길이를 구하는 속성을 채우세요.

int[] arr = {1, 2, 3, 4, 5};
System.out.println(arr.___);',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'length',
'배열의 길이는 .length 속성으로 구합니다.',
'배열 크기를 반환하는 필드(메서드 아님, 괄호 없음)입니다.', false);

-- =============================================
-- STAGE 2: 클래스, 상속, 인터페이스, 추상클래스
-- =============================================

-- [객관식]
INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('클래스', 2, 2,
'다음 코드에서 오류 원인은?

public class Car {
    private int speed;
}
Car c = new Car();
c.speed = 100;',
'MULTIPLE_CHOICE',
'Car 클래스 문법 오류',
'private 필드는 외부 직접 접근 불가',
'new Car() 생성자 없음',
'int에 100 저장 불가',
'B',
'private 접근 제한자가 붙은 필드는 해당 클래스 내부에서만 접근 가능합니다.',
NULL, true);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('상속', 2, 2,
'다음 코드의 출력 결과는?

class Animal {
    void sound() {
        System.out.println("...");
    }
}
class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("멍멍");
    }
}
Animal a = new Dog();
a.sound();',
'MULTIPLE_CHOICE',
'"..."', '"멍멍"', '오류 발생', 'null',
'B',
'다형성에 의해 실제 객체 타입(Dog)의 오버라이딩된 메서드가 호출됩니다.',
NULL, true);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('인터페이스', 3, 2,
'인터페이스에 대한 설명으로 틀린 것은?',
'MULTIPLE_CHOICE',
'다중 구현이 가능하다',
'new로 직접 인스턴스 생성 가능하다',
'모든 필드는 public static final이다',
'Java 8부터 default 메서드를 가질 수 있다',
'B',
'인터페이스는 추상적 설계도이므로 new 키워드로 직접 인스턴스를 생성할 수 없습니다.',
NULL, false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('추상클래스', 3, 2,
'추상 클래스에 대한 설명으로 올바른 것은?',
'MULTIPLE_CHOICE',
'인스턴스를 직접 생성할 수 있다',
'추상 메서드만 가질 수 있다',
'abstract 키워드로 선언하며 직접 인스턴스화 불가',
'다중 상속이 가능하다',
'C',
'추상 클래스는 abstract 키워드로 선언하며 직접 인스턴스를 생성할 수 없습니다. 일반 메서드도 가질 수 있습니다.',
NULL, false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('다형성', 3, 2,
'instanceof 연산자의 역할은?',
'MULTIPLE_CHOICE',
'두 객체 내용이 같은지 비교',
'객체가 특정 클래스/인터페이스 타입인지 확인',
'객체의 메모리 주소 비교',
'클래스 인스턴스 생성',
'B',
'instanceof는 객체가 특정 타입인지 확인합니다. 강제 타입 변환 전 안전성 확인에 사용합니다.',
NULL, false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('클래스', 2, 2,
'메서드 오버로딩 조건으로 올바른 것은?',
'MULTIPLE_CHOICE',
'반환 타입이 달라야 한다',
'메서드 이름이 달라야 한다',
'매개변수 타입이나 개수가 달라야 한다',
'접근 제한자가 달라야 한다',
'C',
'오버로딩은 같은 이름의 메서드를 여러 개 정의할 때 매개변수 타입, 개수, 순서가 달라야 합니다.',
NULL, false);

-- [빈칸 채우기]
INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('상속', 2, 2,
'클래스 상속 키워드를 채우세요.

class Dog ___ Animal {
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'extends',
'Java에서 클래스 상속은 extends 키워드를 사용합니다.',
'클래스가 다른 클래스를 상속받을 때 사용하는 키워드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('인터페이스', 2, 2,
'인터페이스 구현 키워드를 채우세요.

class Bird ___ Flyable {
    public void fly() { }
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'implements',
'클래스가 인터페이스를 구현할 때는 implements 키워드를 사용합니다.',
'클래스가 인터페이스를 구현할 때 사용하는 키워드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('클래스', 2, 2,
'생성자에서 자기 자신의 필드를 참조하는 키워드를 채우세요.

public Person(String name) {
    ___.name = name;
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'this',
'this는 현재 객체 자신을 참조하는 키워드입니다.',
'현재 클래스의 인스턴스를 참조하는 키워드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('상속', 2, 2,
'자식 생성자에서 부모 생성자를 호출하는 코드를 채우세요.

public Dog(String name) {
    ___(name);
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'super',
'super()는 부모 클래스의 생성자를 호출합니다. 반드시 자식 생성자 첫 줄에 와야 합니다.',
'부모 클래스를 참조하는 키워드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('추상클래스', 3, 2,
'추상 메서드 선언 키워드를 채우세요.

public ___ class Shape {
    public ___ double area();
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'abstract',
'추상 클래스와 추상 메서드 모두 abstract 키워드를 사용합니다.',
'구현 없이 선언만 하는 메서드/클래스에 사용하는 키워드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('클래스', 2, 2,
'외부에서 접근 불가능한 접근 제한자를 채우세요.

public class BankAccount {
    ___ int balance;
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'private',
'private은 해당 클래스 내부에서만 접근 가능한 가장 강한 접근 제한자입니다.',
'캡슐화를 위해 필드에 사용하는 접근 제한자입니다.', false);

-- =============================================
-- STAGE 3: 예외처리, 컬렉션, 제네릭, 람다, 스트림
-- =============================================

-- [객관식]
INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('예외처리', 2, 3,
'Checked Exception에 해당하는 것은?',
'MULTIPLE_CHOICE',
'NullPointerException',
'ArrayIndexOutOfBoundsException',
'IOException',
'ClassCastException',
'C',
'IOException은 컴파일러가 예외 처리를 강제하는 Checked Exception입니다.',
NULL, false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('컬렉션', 2, 3,
'ArrayList에 대한 설명으로 틀린 것은?',
'MULTIPLE_CHOICE',
'크기가 동적으로 변한다',
'인덱스로 요소에 접근 가능하다',
'중복 요소를 저장할 수 없다',
'null 값을 저장할 수 있다',
'C',
'ArrayList는 중복 요소를 허용합니다. 중복 불가는 Set(HashSet 등)입니다.',
NULL, false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('컬렉션', 3, 3,
'HashMap에서 같은 키로 put()을 두 번 하면?',
'MULTIPLE_CHOICE',
'예외가 발생한다',
'두 값이 모두 저장된다',
'기존 값이 새 값으로 덮어써진다',
'두 번째 put()이 무시된다',
'C',
'HashMap의 키는 유일합니다. 같은 키로 put()하면 기존 값이 새 값으로 덮어써집니다.',
NULL, false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('제네릭', 3, 3,
'제네릭의 장점으로 올바르지 않은 것은?',
'MULTIPLE_CHOICE',
'타입 안전성을 높인다',
'코드 재사용성을 높인다',
'실행 속도를 높인다',
'불필요한 타입 변환을 줄인다',
'C',
'제네릭은 컴파일 시점 타입 체크로 안전성과 재사용성을 높이지만 실행 속도와는 무관합니다.',
NULL, false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('스트림', 3, 3,
'Stream API의 중간 연산(intermediate operation)은?',
'MULTIPLE_CHOICE',
'collect()', 'forEach()', 'filter()', 'count()',
'C',
'filter()는 중간 연산입니다. collect(), forEach(), count()는 최종 연산(terminal operation)입니다.',
NULL, false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('람다', 3, 3,
'람다 표현식이 대체할 수 있는 것은?',
'MULTIPLE_CHOICE',
'모든 인터페이스의 익명 객체',
'추상 메서드가 하나인 함수형 인터페이스의 익명 객체',
'추상 클래스의 익명 객체',
'일반 클래스의 객체',
'B',
'람다는 @FunctionalInterface, 즉 추상 메서드가 단 하나인 인터페이스의 익명 구현체를 대체합니다.',
NULL, false);

-- [빈칸 채우기]
INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('예외처리', 2, 3,
'예외를 잡는 키워드를 채우세요.

try {
    int n = Integer.parseInt("abc");
} ___ (NumberFormatException e) {
    System.out.println("오류!");
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'catch',
'try 블록에서 발생한 예외를 catch 블록에서 처리합니다.',
'try와 함께 사용하여 예외를 잡는 키워드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('예외처리', 2, 3,
'예외 발생 여부와 관계없이 항상 실행되는 블록 키워드를 채우세요.

try {
    // 코드
} catch (Exception e) {
    // 처리
} ___ {
    // 항상 실행
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'finally',
'finally 블록은 예외 발생 여부와 무관하게 항상 실행됩니다.',
'try-catch 이후 항상 실행되는 블록의 키워드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('컬렉션', 2, 3,
'ArrayList에 요소를 추가하는 메서드를 채우세요.

ArrayList<String> list = new ArrayList<>();
list.___(''Java'');',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'add',
'ArrayList에 요소를 추가할 때는 add() 메서드를 사용합니다.',
'컬렉션에 요소를 추가하는 기본 메서드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('람다', 3, 3,
'Runnable을 람다로 구현하는 빈칸을 채우세요.

Runnable r = ___ -> System.out.println("Hello");',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'()',
'매개변수가 없는 람다는 ()로 표현합니다. Runnable의 run()은 매개변수가 없습니다.',
'매개변수가 없는 람다 표현식의 시작 부분입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('스트림', 3, 3,
'스트림에서 조건에 맞는 요소만 걸러내는 메서드를 채우세요.

list.stream()
    .___(n -> n > 0)
    .forEach(System.out::println);',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'filter',
'filter()는 조건에 맞는 요소만 남기는 중간 연산입니다.',
'스트림에서 조건으로 요소를 거르는 중간 연산 메서드입니다.', false);

INSERT INTO questions (concept, difficulty, stage, question_text, question_type, option_a, option_b, option_c, option_d, correct_answer, explanation, hint, has_code) VALUES
('제네릭', 3, 3,
'제네릭 타입 매개변수를 채우세요.

public class Box___ {
    private T item;
    public void set(T item) {
        this.item = item;
    }
}',
'CODE_INPUT',
NULL, NULL, NULL, NULL,
'<T>',
'제네릭 클래스는 클래스명 뒤에 <T>와 같이 타입 매개변수를 선언합니다.',
'제네릭 타입 선언은 꺾쇠 괄호 안에 타입 매개변수를 씁니다.', false);
