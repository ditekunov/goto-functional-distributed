### Advanced стек задач на функциональное программирование в Scala:

#### 1) Дублицируйте каждый элемент списка N раз

Пример:

scala> duplicateN(3, List('a, 'b, 'c, 'c, 'd))
res0: List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
        
#### 2) Реализуйте метод flatten

#### 3) Получите срез листа (функция slice в Python)

#### 4) Поверните лист на N значений влево

Пример:

scala> rotate(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))
res0: List[Char] = List('d', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'a', 'b', 'c')

scala> rotate(-2, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))
res1: List[Char] = List('j', 'k', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')
