n = int(input())
scores = list(map(int, input().split()))
m = max(scores)
modified_scores = [score/m*100 for score in scores]
print(sum(modified_scores) / n)