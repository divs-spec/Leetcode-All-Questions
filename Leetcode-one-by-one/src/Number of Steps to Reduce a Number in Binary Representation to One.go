func numSteps(s string) int { steps, carry := 0, 0; for i := len(s)-1; i > 0; i-- { if int(s[i]-'0')+carry == 1 { steps += 2; carry = 1 } else { steps++ } }; return steps + carry }
