from collections import deque


if __name__ == "__main__":
    N, K = map(int, input().split())
    if N > K:
        print(N-K)
    else:
        queue = deque()
        queue.append((N, 0))
        visited = [False] * 100001
        while queue:
            now_pos, t = queue.popleft()
            if now_pos == K:
                print(t)
                break
            if visited[now_pos]: continue
            else: visited[now_pos] = True
            if 0 < (now_pos * 2) < (2*K - now_pos) and (now_pos * 2) <= 100000:
                queue.append((now_pos * 2, t+1))
            if (now_pos + 1) <= K:
                queue.append((now_pos + 1, t+1))
            if (now_pos - 1) >= 0: 
                queue.append((now_pos - 1, t+1))