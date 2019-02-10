class UserPolicy < ApplicationPolicy
  def index?
    true
  end

  def show?
    record == user
  end

  def create?
    true
  end

  def update?
    record == user
  end

  def destroy?
    record == user
  end
end
