### Теперь немного разбиения на части и масштабирования без привязки к условиям:

#### 0) Вам даны две функции:

        def zero(s):
            if s[0] == "0":
                return s[1:]

        def one(s):
            if s[0] == "1":
                return s[1:]

zero() принимает строку s. Если первый символ – 0, то возвращает остаток строки. Если нет – тогда None. one() делает то же самое, если первый символ – 1.

Представим функцию rule_sequence(). Она принимает строку и список из функций-правил, состоящий из функций zero и one. Она вызывает первое правило, передавая ему строку. Если не возвращено None, то берёт возвращённое значение и вызывает следующее правило. И так далее. Если возвращается None, rule_sequence() останавливается и возвращает None. Иначе – значение последнего правила.

Примеры входных и выходных данных:

        print rule_sequence('0101', [zero, one, zero])
        # => 1

        print rule_sequence('0101', [zero, zero])
        # => None
        
        
#### 1) Пайплайны (pipelines)

Следующий цикл изменяет словари, содержащие имя, неправильную страну происхождения и статус беженцев (тут могла бы быть ваша политиче).

    residents = [{'name': 'Johnny', 'country': 'USA', 'active': False},
             {'name': 'bonny1', 'country': 'USA', 'active': False},
             {'name': 'winnie', 'country': 'USA', 'active': True}]

    def format_residents(residents):
        for resident in residents:
            resident['country'] = 'Canada'
            resident['name'] = resident['name'].replace('1', '')
            resident['name'] = resident['name'].title()

    format_residents(residents)

    print residents

Название функции «format» слишком общее. И вообще, код вызывает некоторое беспокойство. В одном цикле происходят три разные вещи. Значение ключа 'country' меняется на 'Canada'. Убираются точки и первая буква имени меняется на заглавную. Сложно понять, что код должен делать, и сложно сказать, делает ли он это. Его тяжело использовать, тестировать и распараллеливать.

Сравните:

    print pipeline_each(residents, [set_canada_as_country,
                                strip_punctuation_from_name,
                                capitalize_names])


Всё просто. Вспомогательные функции выглядят функциональными, потому что они связаны в цепочку. Выход предыдущей – вход следующей. Их просто проверить, использовать повторно, проверять и распараллеливать.

pipeline_each() перебирает группы по одной, и передаёт их функциям преобразования, вроде set_canada_as_country(). После применения функции ко всем группам, pipeline_each() делает из них список и передаёт следующей.

Посмотрим на функции преобразования.

    def assoc(_d, key, value):
        from copy import deepcopy
        d = deepcopy(_d)
        d[key] = value
        return d

    def set_canada_as_country(resident):
        return assoc(resident, 'country', "Canada")

    def strip_punctuation_from_name(resident):
        return assoc(resident, 'name', resident['name'].replace('1', ''))

    def capitalize_names(resident):
        return assoc(resident, 'name', resident['name'].title())


Каждая связывает ключ группы с новым значением. Без изменения оригинальных данных это тяжело сделать, поэтому мы решаем это с помощью assoc(). Она использует deepcopy() для создания копии переданного словаря. Каждая функция преобразовывает копию и возвращает эту копию.

Всё вроде как нормально. Оригиналы данных защищены от изменений. Но в коде есть два потенциальных места для изменений данных. В strip_punctuation_from_name() создаётся имя без точек через вызов calling replace() с оригинальным именем. В capitalize_names() создаётся имя с первой прописной буквой на основе title() и оригинального имени. Если replace и time не функциональны, то и strip_punctuation_from_name() с capitalize_names() не функциональны.

К счастью, они функциональны. В Python строки неизменяемы. Эти функции работают с копиями строк. Уфф, слава богу.

Такой контраст между строками и словарями (их изменяемостью) в Python демонстрирует преимущества языков типа Scala. Там программисту не надо думать, не изменит ли он данные. Не изменит.

#### Попробуйте сделать функцию pipeline_each. Задумайтесь над последовательностью операций. Группы – в массиве, передаются по одной для первой функции преобразования. Затем полученный массив передаётся по одной штучке для второй функции, и так далее.
