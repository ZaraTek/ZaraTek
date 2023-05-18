# Tic Tac Toe
print("Welcome to Tic Tac Toe")

board = [
    [' ',' ',' ','a',' ',' ',' ','b',' ',' ',' ','c',' ',' '],
    [' ',' ',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ',' '],
    ['1',' ',' ','-',' ','|',' ','-',' ','|',' ','-',' ',' '],
    ['_','_','_','_','_','_','_','_','_','_','_','_','_','_'],
    [' ',' ',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ',' '],
    ['2',' ',' ','-',' ','|',' ','-',' ','|',' ','-',' ',' '],
    ['_','_','_','_','_','_','_','_','_','_','_','_','_','_'],
    [' ',' ',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ',' '],
    ['3',' ',' ','-',' ','|',' ','-',' ','|',' ','-',' ',' '],
    [' ',' ',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ',' ']
]

def printBoard():
    for row in board:
        for element in row:
            print (element, end=" ")
        print()

def posConvert(userInput):
    rowU = userInput[0:1]
    colU = userInput[2]
    while rowU != '1' and rowU != '2' and rowU != '3' and colU != 'a' and colU != 'b' and colU != 'c':
        userInput = input("Please enter a valid input: ")
        rowU = userInput[0:1]
        colU = userInput[2]
        

    # convert row
    if rowU == '1':
        row = 2
    elif rowU == '2':
        row = 5
    elif rowU == '3':
        row = 8
    else:
        return 0, 0
    # convert column
    if colU == 'a':
        col = 3
    elif colU == 'b':
        col = 7
    elif colU == 'c':
        col = 11
    else:
        return 0
    
    return row, col

def checkWin():
    winningCombinations = [
        # Rows
        [[2,3], [2,7], [2,11]],
        [[5,3], [5,7], [5,11]],
        [[8,3], [8,7], [8,11]],
        # Columns
        [[2,3], [5,3], [8,3]],
        [[2,7], [5,7], [8,7]],
        [[2,11], [5,11], [8,11]],
        #Diagonals
        [[2,3], [5,7], [8,11]],
        [[2,11], [5,7], [8,3]]
    ]
    for combination in winningCombinations:
        symbol = board[combination[0][0]][combination[0][1]]
        if symbol != '-' and all(board[pos[0]][pos[1]] == symbol for pos in combination):
                return True
    return False

player = 'X'
gameOver = False
printBoard()

while (gameOver == False):
    print("Player ", player, "'s turn")
    userInput = input("Input the row letter and the column number seperated by a space: ")
    row, col = posConvert(userInput)

    while row == 0 or col == 0 or board[row][col] != '-':
        userInput = input("Please enter a valid input: ")
        row, col = posConvert(userInput)
    
    board[row][col] = player
    printBoard()

    if checkWin():
        gameOver = True
        print("Game over! Player ", player, " wins!")
    playableSpaces = [
        [2,3], [2,7], [2,11],
        [5,3], [5,7], [5,11],
        [8,3], [8,7], [8,11],
    ]
    playedSpacesCount = 0
    for space in playableSpaces:
        row, col = space
        if board[row][col] != '-': playedSpacesCount += 1
    if playedSpacesCount == 9: 
        gameOver = True
        print("Game over! Cat's game!")

    if player == 'X':
        player = 'O'
    else:
        player = 'X'
