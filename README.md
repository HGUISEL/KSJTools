# KSJTools

KSJTools is tool for compute performance of spectral clustering based unsupervised classifier. It takes original arff file, result file, attribute name, and poditive label value. 

Original arff file contains class label. Result file is text file of matrix, that have predicted results with spectral clustering based unsupervised classifier. 

## Options

-  -f,--arffFile (original arff file)   recieves the original arff file path that has a defect classifier label 
- -l,--label (attribute name)   label (Class attrubite) name
- -p,--poslabel (positive label value)   String value of bug label
- -r,--resultFile (result file)   recieves the text file that contains result from spectral classifier with R

## Example Tests

```powershell
./KSJTools -f /Users/sujin/Desktop/ISEL/datasets/4.2.2_different_machine_learner/HM.arff -r /Users/sujin/Desktop/ISEL/datasets/4.2.2-removed_class_label/4.2.2-results/HM-0-result.txt -p buggy -l class
```

