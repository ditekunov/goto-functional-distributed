### Верной дорогой идете, товарищи!

1) Вернитесь к последней задаче и реализуйте функцию call() в соответствии с данной логикой:

Все три функции преобразования в результате меняют конкретное поле у группы. call() можно использовать, чтобы создать абстракцию для этого. Она принимает функцию и ключ, к которому она будет применена.

    set_canada_as_country = call(lambda x: 'Canada', 'country')
    strip_punctuation_from_name = call(lambda x: x.replace('1', ''), 'name')
    capitalize_names = call(str.title, 'name')

    print pipeline_each(bands, [set_canada_as_country,
                                strip_punctuation_from_name,
                                capitalize_names])
                                
                                
Подсказка: пользуййтесь функцией alloc()

    def assoc(_d, key, value):
        from copy import deepcopy
        d = deepcopy(_d)
        d[key] = value
        return d
        
        
        
2) Добиваем монстра (задачу):

pluck() принимает список ключей, которые надо извлечь из записей. Попробуйте её написать. Это будет функция высшего порядка.

    print pipeline_each(bands, [call(lambda x: 'Canada', 'country'),
                                call(lambda x: x.replace('1', ''), 'name'),
                                call(str.title, 'name'),
                                pluck(['name', 'country'])])
                                
                                
3) Рекурсивно найдите сумму всех четных чисел Фибоначчи.

Условие: сложность O(N) или выше.

