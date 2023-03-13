N = int(input())
l = []

if N <= 10000:
    for i in range(N):
        k = int(input())
        if k < 1000:
            l.append(k)
else:
    print("Количество элементов слишком большое")

min = 1000000
for i in range(len(l)):
        for j in range(i+4, len(l)):
            if i+j < len(l):
                if l[i] + l[i+j] < min:
                    min = l[i] + l[i+j]

print(min)