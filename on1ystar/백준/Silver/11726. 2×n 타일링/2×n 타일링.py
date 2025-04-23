n = int(input())
x = 1
y = 2
if n == 1: print(x)
elif n == 2: print(y)
else:
    while n != 2:
        temp = y
        y = y + x
        x = temp
        n -= 1
    print(y%10007)