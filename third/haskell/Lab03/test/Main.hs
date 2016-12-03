{-# LANGUAGE StandaloneDeriving #-}

module Main where

import Test.HUnit
import Test.Framework as TF (defaultMain, testGroup, Test)
import Test.Framework.Providers.HUnit (testCase)
import Test.Framework.Providers.QuickCheck2 (testProperty)
import Data.Char

import Lab03

{- HUnit Tests -}

test_show_empty  = show empCI @?= "Empty"
test_show_leaf_a1 = show leaf_a1 @?= "Leaf 'a' 1"
test_show_leaf_1a = show leaf_1a @?= "Leaf 1 'a'"
test_show_br_emp_a1 = show br_emp_a1 @?= "Branch Empty 'a' 1 Empty"
test_show_br_left_a1_b2
 = show br_left_a1_b2 @?= "Branch (Leaf 'a' 1) 'b' 2 Empty"
test_show_br_right_a1_b2
 = show br_right_a1_b2 @?= "Branch Empty 'a' 1 (Leaf 'b' 2)"
test_show_emptyX4
 = show emptyX4 
   @?= 
   "Branch (Branch Empty () () Empty) () () (Branch Empty () () Empty)"

test_map_emp = treeMap ord chr empCI @?= empIC
test_map_leaf = treeMap alphaOrd alphaChr leaf_b2 @?= leaf_2b
test_map_branch
  = treeMap (const 0) (const []) emptyX4
    @?=
    (nullX4 :: BinTree Int [Int])
  where
    nullX2 = Branch Empty 0 [] Empty
    nullX4 = Branch nullX2 0 [] nullX2

test_insert_emp = ins1 @?= Leaf 'c' 3
test_insert_leaf = ins2 @?= Branch (Leaf 'c' 3) 'd' 4 Empty
test_insert_br_new = ins3 @?= Branch (Branch Empty 'b' 2 (Leaf 'c' 3)) 'd' 4 Empty
test_insert_br_old = ins4 @?= Branch (Branch Empty 'b' 2 (Leaf 'c' 99)) 'd' 4 Empty

test_lkp_emp = treeLookup 'a' empCI @?= Nothing
test_lkp_leaf_fail = treeLookup 'a' leaf_b2 @?= Nothing
test_lkp_leaf_ok = treeLookup 'b' leaf_b2 @?= Just 2
test_lkp_branch = treeLookup 'c' ins4 @?= Just 99

{- Test Main -}

main = defaultMain tests

tests :: [TF.Test]
tests 
  = [ testGroup "\nTotal Marks Available: 20\nShow Tests (8 marks)" 
      [ testCase "Show Empty {1 mark}" $ test_show_empty
      , testCase "Show Leaf a 1 {1 mark}" $ test_show_leaf_a1
      , testCase "Show Leaf 1 a {1 mark}" $ test_show_leaf_1a
      , testCase "Show Br Empty a 1 {1 mark}" $ test_show_br_emp_a1
      , testCase "Show Br Left a 1 b 2 {1 mark}" $ test_show_br_left_a1_b2
      , testCase "Show Br Right a 1 b 2 {1 mark}" $ test_show_br_right_a1_b2
      , testCase "Show Empty X 4 {2 marks}" $ test_show_emptyX4
      ]
    , testGroup "Map Tests (3 marks)" 
      [ testCase "Map Empty {1 mark}" $ test_map_emp
      , testCase "Map Leaf {1 mark}" $ test_map_leaf
      , testCase "Map Branch {1 mark}" $ test_map_branch
      ]
    , testGroup "Insert Tests (5 marks)" 
      [ testCase "Insert Empty {1 mark}" $ test_insert_emp
      , testCase "Insert Leaf {1 mark}" $ test_insert_leaf
      , testCase "Insert Branch (New) {2 marks}" $ test_insert_br_new
      , testCase "Insert Branch (Old) {1 mark}" $ test_insert_br_old
      ]
    , testGroup "Lookup Tests (4 marks)" 
      [ testCase "Lookup Empty {1 mark}" $ test_lkp_emp
      , testCase "Lookup Leaf (Fail) {1 mark}" $ test_lkp_leaf_fail
      , testCase "Lookup Leaf (Ok) {1 mark}" $ test_lkp_leaf_ok
      , testCase "Lookup Branch {1 mark}" $ test_lkp_branch
      ]
    ]
