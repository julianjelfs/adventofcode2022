(ns advent-of-code.day-04-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day-04 :refer [part-1 part-2 to-ranges]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected nil]
    (is (= expected (part-1 (slurp (resource "day-04-example.txt")))))))

(deftest test-to-ranges
  (let [expected nil]
    (is (= expected (to-ranges ("2-6,4-8"))))))


(deftest part2
  (let [expected nil]
    (is (= expected (part-2 (slurp (resource "day-04-example.txt")))))))
